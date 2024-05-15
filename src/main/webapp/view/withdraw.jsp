<%--
  Created by IntelliJ IDEA.
  User: sarar
  Date: 13-05-2024
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Cache-control" content="public">
    <meta name="viewport" content="width=device-width,initial-scale=1, minimum-scale=1">
    <meta name="description"
          content="An e-wallet that allows the user to manage, transfer and withdraw money in a convenient and easy way.">
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous"> -->

    <link rel="icon" href="../favicon.ico" type="image/x-icon">
    <title>NovaWallet | Withdrawal</title>
    <script type="module" crossorigin="" src="<%= request.getContextPath() %>/resources/js/app.js"></script>
    <link rel="stylesheet" crossorigin="" href="<%= request.getContextPath() %>/resources/css/style.css" type="text/css">
</head>

<body>
<div id="root" class="root">
    <div class="withdraw-wrapper">
        <header>
            <div class="header-container">
                <h1 class="logo"><a href="${pageContext.request.contextPath}/home">Nova<span>Wallet</span></a></h1>
                <nav class="site-nav">
                    <button class="menu-toggle" aria-controls="primary-navigation" aria-expanded="false"><span
                            class="visually-hidden"></span>
                        <div class="hamburger" aria-hidden="true"></div>
                    </button>
                    <ul class="primary-navigation" id="primary-navigation" data-state="closed">
                        <a href="${pageContext.request.contextPath}/home"><li>Home</li></a>
                        <a href="${pageContext.request.contextPath}/deposit"><li>Deposit</li></a>
                        <a href="${pageContext.request.contextPath}/withdraw"><li>Withdraw</li></a>
                        <a href="${pageContext.request.contextPath}/transfer"><li>Send Money</li></a>
                        <a href="${pageContext.request.contextPath}/transactions"><li>Transactions</li></a>
                        <a href="${pageContext.request.contextPath}/logout"><li>Logout</li></a>
                    </ul>
                </nav>
            </div>
        </header>
        <main class="withdraw-content-wrapper">
            <h2>Withdraw funds from your account</h2>
            <div class="withdraw-balance-div">
                <div class="balance-wrapper"><h3 class="balance-title">your balance is:</h3>
                    <h2 class="balance-subtitle">${currency} ${balance}</h2></div>
            </div>
            <form style="display: contents;" method="post" action="../withdraw">
                <div class="input-wrapper">
                    <input class="input input-text" type="number" id="withdraw-amount"
                           autocomplete="new-password" placeholder=" " name="amount" required=""
                           step="0.01" min="0.01" max="${balanceBD}">
                    <label class="label input-label" for="withdraw-amount">Enter amount</label>
                </div>
                <input class="button" type="submit" id="button-withdraw" value="Withdraw"/>
            </form>
        </main>
        <dialog class="modal msg-modal">
            <div class="wrapper dialog-wrapper"><h3 class="title">Error</h3>
                <p>Invalid amount</p>
                <button class="button close-button">Ok</button>
            </div>
        </dialog>
        <dialog class="modal msg-modal">
            <div class="wrapper dialog-wrapper"><h3 class="title">Successful operation</h3>
                <p>Funds added to you account</p>
                <button class="button close-button">Ok</button>
            </div>
        </dialog>
        <footer>
            <div class="footer-container"><p>Designed and developed by ©Sara Rioseco 2024</p></div>
        </footer>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
  crossorigin="anonymous"></script> -->


</body>
</html>
