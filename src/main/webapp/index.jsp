<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Cache-control" content="public">
    <meta name="viewport" content="width=device-width,initial-scale=1, minimum-scale=1">
    <meta name="description"
          content="An e-wallet that allows the user to manage, transfer and deposit money in a convenient and easy way.">
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <title>NovaWallet</title>
    <script type="module" crossorigin="" src="./resources/js/app.js"></script>
    <link rel="stylesheet" crossorigin="" href="./resources/css/style.css" type="text/css">
</head>

<body>
<div id="root" class="root">
    <div class="login-wrapper">
        <form class="login-content-wrapper" method="post" action="home"><h1>Nova<span>Wallet</span></h1>
            <div class="input-wrapper visually-hidden" hidden="">
                <input class="input input-text" type="text"
                    id="login-username" autocomplete="new-password"
                    placeholder=" " name="username">
                <label class="label input-label" for="login-username">Enter your username</label>
            </div>
            <div class="input-wrapper">
                <input class="input input-text" type="search" id="login-email"
                      autocomplete="new-password" placeholder=" " name="mail" required>
                <label class="label input-label" for="login-email">Enter your Email</label>
            </div>
            <div class="input-wrapper">
                <input class="input input-text" type="password" id="login-password"
                    autocomplete="new-password" placeholder=" " name="pass" required>
                <label class="label input-label" for="login-password">Enter
                your Password</label></div>
            <input type="submit" class="button" id="button-login" value="Login" />
            <p class="login-text">Don't have an account?
                <span><a href="signup.jsp">Sign up here.</a></span></p>
            <dialog class="modal msg-modal">
                <div class="wrapper dialog-wrapper"><h3 class="title">Error</h3>
                    <p>Invalid credentials</p>
                    <button class="button close-button">Ok</button>
                </div>
            </dialog>
        </form>
    </div>
</div>
</body>
</html>