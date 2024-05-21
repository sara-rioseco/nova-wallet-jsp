<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Cache-control" content="public">
    <meta name="viewport" content="width=device-width,initial-scale=1, minimum-scale=1">
    <meta name="description"
          content="An e-wallet that allows the user to manage, transfer and deposit money in a convenient and easy way.">
    <link rel="icon" href="./favicon.ico" type="image/x-icon">
    <title>NovaWallet | Send Money</title>
    <script type="module" crossorigin="" src="<%= request.getContextPath() %>/resources/js/app.js"></script>
    <link rel="stylesheet" crossorigin="" href="<%= request.getContextPath() %>/resources/css/style.css" type="text/css">
</head>

<body>
<div id="root" class="root">
    <div class="transfer-wrapper">
        <header>
            <div class="header-container">
                <h1 class="logo"><a href="${pageContext.request.contextPath}/home">Nova<span>Wallet</span></a></h1>
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
        <main class="transfer-content-wrapper"><h2>Send Money</h2>
            <div class="input-wrapper"><input class="input input-text" type="text" id="contact-search"
                                              autocomplete="new-password" placeholder=" " name="contact"
                                              required=""><label class="label input-label" for="contact-search">Search
                contact</label></div>
            <section class="contacts-wrapper">
                <form method="post" action="transfer" style="display:contents;">
                    <div class="contacts-list">
                        <c:if test="${contacts.size() > 0}">
                            <c:forEach items="${contacts}" var="item">
                                <div class="contact-wrapper">
                                    <input type="radio" id="contact-${item.id}" name="contact" value=${item.id} class="contact-item contact-${item.id}" style="display: none;" required>
                                    <label class="contact-label" for="contact-${item.id}">
                                            ${item.name} ${item.last}
                                        <span>${item.mail}</span>
                                    </label>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${contacts.size() == 0}">
                            <div class="contact-wrapper">
                                <h3 id="contact-0" class="contact-item no-contact"> You don't have any contacts yet. </h3>
                            </div>
                        </c:if>

                    </div>
                    <div class="input-wrapper">
                        <input class="input input-text" type="number" id="transfer-amount" autocomplete="new-password"
                               placeholder=" " name="amount" required="" step="0.01" min="0.01" max="${balanceBD}">
                        <label class="label input-label" for="transfer-amount">
                            Enter amount
                        </label>
                    </div>
                    <input class="button" type="submit" id="button-SendMoney" value="Send Money"/>
                </form>
            </section>
        </main>
        <dialog class="modal msg-modal">
            <div class="wrapper dialog-wrapper"><h3 class="title">Error</h3>
                <p>Please select a contact</p>
                <button class="button close-button">Ok</button>
            </div>
        </dialog>
        <dialog class="modal msg-modal">
            <div class="wrapper dialog-wrapper"><h3 class="title">Error</h3>
                <p>Please enter a transfer amount</p>
                <button class="button close-button">Ok</button>
            </div>
        </dialog>
        <dialog class="modal msg-modal">
            <div class="wrapper dialog-wrapper"><h3 class="title">Error</h3>
                <p>Invalid transfer amount</p>
                <button class="button close-button">Ok</button>
            </div>
        </dialog>
        <footer>
            <div class="footer-container"><p>Designed and developed by ©Sara Rioseco 2024</p></div>
        </footer>
    </div>
</div>
</body>
</html>