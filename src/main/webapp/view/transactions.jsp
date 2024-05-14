<%--
  Created by IntelliJ IDEA.
  User: sarar
  Date: 13-05-2024
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Cache-control" content="public">
    <meta name="viewport" content="width=device-width,initial-scale=1, minimum-scale=1">
    <meta name="description" content="An e-wallet that allows the user to manage, transfer and deposit money in a convenient and easy way.">
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous"> -->

    <link rel="icon" href="../favicon.ico" type="image/x-icon">
    <title>NovaWallet | Transactions</title>
    <script type="module" crossorigin="" src="../resources/js/app.js"></script>
    <link rel="stylesheet" crossorigin="" href="../resources/css/style.css">
</head>

<body>
<div id="root" class="root"><div class="transactions-wrapper"><header><div class="header-container"><h1 class="logo">Nova<span>Wallet</span></h1><nav class="site-nav"><button class="menu-toggle" aria-controls="primary-navigation" aria-expanded="false"><span class="visually-hidden"></span><div class="hamburger" aria-hidden="true"></div></button><ul class="primary-navigation" id="primary-navigation" data-state="closed"><li>Home</li><li>Deposit</li><li>Send Money</li><li>Transactions</li><li>Logout</li></ul></nav></div></header><main class="transactions-content-wrapper"><h2 class="transactions-title">Transaction History</h2><section class="transactions-history-wrapper"><div class="history-item-wrapper"><div class="history-item-title"><h3>Transfer</h3><h3>-$300.00 </h3></div><p class="history-item-subtitle">March 2, 2024</p></div><div class="history-item-wrapper"><div class="history-item-title"><h3>Deposit</h3><h3>$100.00 </h3></div><p class="history-item-subtitle">March 1, 2024</p></div><div class="history-item-wrapper"><div class="history-item-title"><h3>Transfer</h3><h3>-$200.00 </h3></div><p class="history-item-subtitle">February 24, 2024</p></div><div class="history-item-wrapper"><div class="history-item-title"><h3>Deposit</h3><h3>$500.00 </h3></div><p class="history-item-subtitle">February 24, 2024</p></div><div class="history-item-wrapper"><div class="history-item-title"><h3>Deposit</h3><h3>$500.00 </h3></div><p class="history-item-subtitle">February 22, 2024</p></div><div class="history-item-wrapper"><div class="history-item-title"><h3>Transfer</h3><h3>-$500.00 </h3></div><p class="history-item-subtitle">February 22, 2024</p></div><div class="history-item-wrapper"><div class="history-item-title"><h3>Deposit</h3><h3>$500.00 </h3></div><p class="history-item-subtitle">February 20, 2024</p></div></section><h3 class="transactions-balance">Your total balance is: <span>USD$600.00</span></h3></main><footer><div class="footer-container"><p>Designed and developed by ©Sara Rioseco 2024</p></div></footer></div></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
  crossorigin="anonymous"></script> -->


</body></html>
