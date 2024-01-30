# `чуть-чуть про` Реализацию

### Библиотеки & Стек

+ `Spring Boot`
+ `opencsv`
+ `uuid-creator` от [f4b6a3](https://github.com/f4b6a3)
+ `lombok`

`Java: 21`

### Команды

Существует `Command.java`:
```java
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public abstract class Command {
String usage;
String help;

    public Command(String usage, String help) {
        this.usage = usage;
        this.help = help;
    }

    public Command(String usage) {
        this.usage = usage;
        this.help = "no help provided";
    }

    public abstract void execute(Chat chat, Update update);
}

```

От него наследуются все команды, а так же помечаются аннотацией `@Component`, что позволяет в [ChatConfiguration.java](src/main/java/com/serezka/lab/configuration/chat/ChatConfiguration.java) через `@ComponentScan(...)` получить и добавить все созданные команды в `Chat.java` 
[код](src/main/java/com/serezka/lab/lab5/hahdler/Chat.java)

Кусок кода, подтягивающий все команды:

```java
@Bean
    public Chat chat(List<Command> commands,
                     @Value("${chat.name}") String name,
                     @Value("${chat.out.pattern}") String outPattern,
                     @Value("${chat.in.pattern}") String inPattern,
                     @Value("${chat.help.pattern}") String helpPattern,
                     @Qualifier("bufferedConsoleWorker") ConsoleWorker console,
                     @Qualifier("csvFormatWorker") FormatWorker formatWorker) {
        Chat chat = new Chat(name, outPattern, inPattern, helpPattern, console, formatWorker);
        chat.setCommands(commands);
        return chat;
    }
```

### Как работать с CSV

Для Java существует библиотека [opencsv](https://opencsv.sourceforge.net/), с помощью которой и
происходит работа с файлами этого формата в данной реализации

Существует [CsvFormatWorker.java](src/main/java/com/serezka/lab/lab5/io/format/CsvFormatWorker.java),
который наследуется от [FormatWorker.java](src/main/java/com/serezka/lab/lab5/io/format/FormatWorker.java)
и осуществляет непосредственную работу с файлами .csv. Так как
[Product.java](src/main/java/com/serezka/lab/lab5/object/Product.java) содержит поля, которые не являются
неизвестными типами для `opencsv`, необходимо дополнительно дописать конвертер:
[CoordinatesConverter.java](src/main/java/com/serezka/lab/lab5/io/format/converter/CoordinatesConverter.java),
[PersonConverter.java](src/main/java/com/serezka/lab/lab5/io/format/converter/PersonConverter.java),
[LocationConverter.java](src/main/java/com/serezka/lab/lab5/io/format/converter/LocationConverter.java).

### Реализация коллекции

Был создан класс [Data.java](src/main/java/com/serezka/lab/lab5/user/Data.java), который наследуется от 
`LinkedList` и содержит в себе все элементы коллекции.

Так же дополнительно были дописаны методы, которые отделяют команды от
ручного изменения `Data`:

```java
public List<Product> getAscending() {
        return stream()
                .sorted(Product::compareTo)
                .toList();
    }

    public List<Product> removeGreaterThan(Product o) {
        List<Product> toRemove = stream().filter(temp -> temp.compareTo(o) > 0).toList();
        removeAll(toRemove);
        return toRemove;
    }

    public List<Product> reorder() {
        for(int i = 0; i < size()/2; i++) {
            Product buff = get(i);
            set(i, get(size()-i-1));
            set(size()-i-1, buff);
        }

        return this;
    }
```

### Реализация транзакций

Существует [Transaction.java](src/main/java/com/serezka/lab/lab5/transaction/Transaction.java), который хранит в себе поле `Data`

Так же к нему дописан [TransactionManager.java](src/main/java/com/serezka/lab/lab5/transaction/TransactionManager.java),
который хранит в себе все действующие транзакции.

При попытке получить командой коллекцию `Chat` проверяет, есть ли в `TransactionManager`
открытые транзакции и если таковые есть, то подменяет результат `getData()`.

Существует поддержка вложенности транзакций за счет `Stack<Transaction>`
