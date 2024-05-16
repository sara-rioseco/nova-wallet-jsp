<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="jakarta.tags.core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Cache-control" content="public">
    <meta name="viewport" content="width=device-width,initial-scale=1, minimum-scale=1">
    <meta name="description"
          content="An e-wallet that allows the user to manage, transfer and deposit money in a convenient and easy way.">
    <link rel="icon" href="./favicon.ico" type="image/x-icon">
    <title>NovaWallet | Home</title>
    <script type="module" crossorigin="" src="<%= request.getContextPath() %>/resources/js/app.js"></script>
    <link rel="stylesheet" crossorigin="" href="<%= request.getContextPath() %>/resources/css/style.css" type="text/css">
</head>

<body>
<div id="root" class="root">
    <div class="home-wrapper">
        <header>
            <div class="header-container">
                <h1 class="logo"><a href="home">Nova<span>Wallet</span></a></h1>
                <nav class="site-nav">
                    <button class="menu-toggle" id="menu-toggle" aria-controls="primary-navigation" aria-expanded="false"><span
                            class="visually-hidden"></span>
                        <div class="hamburger" id="hamburger" aria-hidden="true"></div>
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
        <main class="home-content-wrapper">
            <section class="home-left"><h2 class="home-left-title home-title">Hello, ${name}</h2>
                <div class="balance-wrapper"><h3 class="balance-title">your balance is:</h3>
                    <h2 class="balance-subtitle">${currency} ${balance}</h2></div>
                <a class="button icon-button credit-card-button" href="${pageContext.request.contextPath}/deposit"><div id="button-credit-card">
                    <i class="fa fa-credit-card home-icon"></i>
                </div></a>
                <a class="button icon-button credit-card-button" href="${pageContext.request.contextPath}/transfer"><div id="button-exchange">
                    <i class="fa fa-exchange home-icon"></i>
                </div></a>
                <a class="button icon-button credit-card-button" href="${pageContext.request.contextPath}/transactions"><div id="button-history">
                    <i class="fa fa-history home-icon"></i>
                </div></a>
                <div id="button-user" class="button icon-button user-button">
                    <i class="fa fa-user home-icon"></i>
                </div>
            </section>
            <section class="home-right"><h3 class="home-right-title home-title">Recent Activity</h3>
                <section class="home-history-wrapper">
                    <c:forEach items="${transactions}" var="item">
                        <div class="history-item-wrapper">
                            <div class="history-item-title">
                                <h3>${item.type}</h3>
                                <h3>${item.symbol}${item.amount}</h3>
                            </div>
                            <p class="history-item-subtitle">${item.date}</p>
                        </div>
                    </c:forEach>
                </section>
            </section>
        </main>
        <dialog class="modal msg-modal" id="user-modal">
            <div class="wrapper dialog-wrapper"><h3 class="title">User information</h3>
                <p>Name: ${name}

                    Lastname: ${last}

                    Email: ${mail}</p>
                <button class="button close-button" id="user-modal-close">Ok</button>
            </div>
        </dialog>
        <footer>
            <div class="footer-container"><p>Designed and developed by ©Sara Rioseco 2024</p></div>
        </footer>
    </div>
</div>
</body>
</html>