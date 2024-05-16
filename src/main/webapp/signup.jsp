<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Cache-control" content="public">
    <meta name="viewport" content="width=device-width,initial-scale=1, minimum-scale=1">
    <meta name="description"
          content="An e-wallet that allows the user to manage, transfer and deposit money in a convenient and easy way.">
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
    <title>NovaWallet</title>
    <script type="module" crossorigin="" src="./resources/js/app.js"></script>
    <link rel="stylesheet" crossorigin="" href="resources/css/style.css" type="text/css">
</head>

<body>
<div id="root" class="root">
    <div class="signup-wrapper">
        <form class="signup-content-wrapper" method="post" action="signup"><h1>Nova<span>Wallet</span></h1>
            <div class="input-wrapper visually-hidden" hidden="">
                <input class="input input-text" type="text" id="signup-username" autocomplete="new-password"
                    placeholder=" " name="username">
                <label class="label input-label" for="signup-username">Enter your username</label>
            </div>
            <div class="input-wrapper">
                <input class="input input-text" type="text" id="signup-name"
                      autocomplete="new-password" placeholder=" " name="firstname" required="">
                <label class="label input-label" for="signup-name">Enter your Name</label>
            </div>
            <div class="input-wrapper">
                <input class="input input-text" type="text" id="signup-lastname"
                    autocomplete="new-password" placeholder=" " name="lastname" required="">
                <label class="label input-label" for="signup-lastname">Enter your Lastname</label></div>
            <div class="input-wrapper">
                <input class="input input-text" type="search" id="signup-email"
                    autocomplete="new-password" placeholder=" " name="email" required="">
                <label class="label input-label" for="signup-email">Enter your Email</label>
            </div>
            <div class="input-wrapper">
                <input class="input input-text" type="password" id="signup-password"
                    autocomplete="new-password" placeholder=" " name="password" required="">
                <label class="label input-label" for="signup-password">Enter your Password</label>
            </div>
            <button id="button-signup">Sign up</button>
            <p class="signup-text">Already have an account?
                <span><a href="index.jsp">Login here.</a></span></p>
            <dialog class="modal msg-modal">
                <div class="wrapper dialog-wrapper"><h3 class="title">User created</h3>
                    <p>successfully</p>
                    <button class="button close-button">Ok</button>
                </div>
            </dialog>
            <dialog class="modal msg-modal">
                <div class="wrapper dialog-wrapper"><h3 class="title">Error</h3>
                    <p>Unable to create user</p>
                    <button class="button close-button">Ok</button>
                </div>
            </dialog>
        </form>
    </div>
</div>
</body>
</html>