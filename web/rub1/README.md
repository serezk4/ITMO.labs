## Практическая часть

### Фрагмент HTML-страницы и набор правил CSS, блокирующий возможность просмотра этой страницы с экранов шириной меньше 1024 пикселя. Вместо страницы на таких устройствах должно показываться сообщение «Разрешение экрана не поддерживаетсям

```html
<!DOCTYPE html>
<html>
<head>
    <style>
        @media (max-width: 1023px) {
            body > * { display: none; }
            body::before { content: "Разрешение экрана не поддерживается"; }
        }
    </style>
</head>
<body>
    <h1>Hi!</h1>
</body>
</html>
```

### FastCGI на жаве

```java
public static void main(String[] args) {
  var fcgiInterface = new FCGIInterface();
  while (fcgiInterface.FCGIaccept() >= 0) {
    var content = """
    <html>
    <head><title>Java FastCGI Hello World</title></head>
    <body><h1>Hello, World!</h1></body>
    </html>""";
    var httpResponse = """
    HTTP/1.1 200 OK
    Content-Type: text/html
    Content-Length: %d
    %s
    """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
    System.out.println(httpResponse);
  }
}
```
