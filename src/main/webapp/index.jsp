<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>NovaWallet</title>
</head>
<body data-bs-theme="dark" class="container-sm d-flex flex-column justify-content-center align-items-center h-100">

<main style="width: 600px">
    <form method="post" action="login">
        <h1 class="display-4 text-center">Welcome to NovaWallet</h1>
        <div class="mb-3">
            <label for="mail" class="form-label">Name</label>
            <input type="email" name="mail" class="form-control" id="mail" placeholder="Write your email" aria-describedby="nameHelp" required>
        </div>
        <div class="mb-3">
            <label for="pass" class="form-label">Password</label>
            <input type="password" name="pass" class="form-control" placeholder="Write your password" id="pass" required>
        </div>
        <input type="submit" class="btn btn-secondary" value="Login" />
    </form>
</main>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>