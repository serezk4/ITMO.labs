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

### Написать фильтр на JSP, который определяет, есть ли у реквеста нужный аттрибут

```java
@WebFilter("/*")
public class MyFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
            throws IOException, ServletException {
        if (req.getAttribute("requiredAttribute") != null) 
            chain.doFilter(req, res);
        else res.getWriter().write("Error: Missing required attribute.");
    }
}
```
web.xml:
```xml
<web-app xmlns="http://java.sun.com/xml/ns/javaee" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" 
         version="3.0">

    <display-name>Attribute Check App</display-name>

    <filter>
        <filter-name>AttributeCheckFilter</filter-name>
        <filter-class>com.example.AttributeCheckFilter</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>AttributeCheckFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

</web-app>
```

### что-то на freemaker

template.ftl
```html
<h1>Welcome, ${user}!</h1>
<ul>
<#list tasks as task>
    <li>${task}</li>
</#list>
</ul>
```

java-class
```java
import freemarker.template.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(Test.class, "/");
        Template template = cfg.getTemplate("template.ftl");
        Map<String, Object> data = Map.of("user", "John", "tasks", List.of("Task 1", "Task 2"));
        template.process(data, new java.io.OutputStreamWriter(System.out));
    }
}
```

### Написать HTML страницу и сервлет, возвращающий странице количество сессий

html-страница
```html
<!DOCTYPE html>
<html>
<head>
    <title>Session Counter</title>
</head>
<body>
    <h1>Session Counter</h1>
    <p>Active sessions: <span id="sessionCount">Loading...</span></p>
    <script>
        fetch('/session-counter').then(response => response.text()).then(data => {
            document.getElementById('sessionCount').innerText = data;
        });
    </script>
</body>
</html>
```

java-сервлет
```java
@WebServlet("/session-counter")
public class SessionCounterServlet extends HttpServlet {
    private static final AtomicInteger sessionCount = new AtomicInteger(0);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write(String.valueOf(sessionCount.get()));
    }

    public void init() {
        getServletContext().addListener(new HttpSessionListener() {
            public void sessionCreated(HttpSessionEvent se) {
                sessionCount.incrementAndGet();
            }

            public void sessionDestroyed(HttpSessionEvent se) {
                sessionCount.decrementAndGet();
            }
        });
    }
}
```

web.xml
```xml
<web-app xmlns="http://java.sun.com/xml/ns/javaee" version="3.0">
    <servlet>
        <servlet-name>SessionCounterServlet</servlet-name>
        <servlet-class>SessionCounterServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>SessionCounterServlet</servlet-name>
        <url-pattern>/session-counter</url-pattern>
    </servlet-mapping>
</web-app>
```

### FastCGI-скрипт на Java, который будет создавать форму для логина и пароля, передавать на сторонний ресурс login.com с помощью Fetch API, затем если логин и пароль корректны, перекидывать на страницу /error

```java
public class FastCGILogin {
    public static void main(String[] args) {
        var fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            var content = """
            <html>
            <body>
                <h1>Login</h1>
                <form id="loginForm">
                    <input name="username" placeholder="Username" required><br>
                    <input name="password" type="password" placeholder="Password" required><br>
                    <button type="button" onclick="submitForm()">Login</button>
                </form>
                <script>
                    async function submitForm() {
                        const res = await fetch('https://login.com/auth', {
                            method: 'POST', body: new FormData(document.getElementById('loginForm'))
                        });
                        if (res.ok) window.location.href = '/error';
                        else alert('Invalid login');
                    }
                </script>
            </body>
            </html>
            """;

            System.out.printf("""
            HTTP/1.1 200 OK
            Content-Type: text/html
            Content-Length: %d

            %s
            """, content.length(), content);
        }
    }
}
```

### Написать страницу на JSP, которая отображается количество сессий от которых приходили запросы за последние 60 секунд

java-class
```java
@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Queue<Long> times = new ConcurrentLinkedQueue<>();
    public static int getSessionCount() {
        long cutoff = System.currentTimeMillis() - 60000;
        times.removeIf(t -> t < cutoff);
        return times.size();
    }
    public void sessionCreated(HttpSessionEvent e) {
        times.add(System.currentTimeMillis());
    }
}
```

jsp-page
```jsp
<%@ page language="java" %>
<%
    int count = com.example.SessionListener.getSessionCount();
%>
<!DOCTYPE html>
<html>
<body>
    <h1>Sessions in Last 60 Seconds: <%= count %></h1>
</body>
</html>
```


