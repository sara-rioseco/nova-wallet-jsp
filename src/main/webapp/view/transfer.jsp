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
    <title>NovaWallet | Send Money</title>
    <script type="module" crossorigin="" src="../resources/js/app.js"></script>
    <link rel="stylesheet" crossorigin="" href="../resources/css/style.css">
</head>

<body>
<div id="root" class="root"><div class="transfer-wrapper"><main class="transfer-content-wrapper"><h2>Send Money</h2><div class="input-wrapper"><input class="input input-text" type="text" id="contact-search" autocomplete="new-password" placeholder=" " name="contact" required=""><label class="label input-label" for="contact-search">Search contact</label></div><section class="contacts-wrapper"><form class="contacts-list"><div class="contact-wrapper"><input type="radio" id="contact-1" name="contact" value="Marta García" class="contact-item contact-1" style="display: none;"><label class="contact-label" for="contact-1">Marta García
    <span>Banco Santander </span><span>#02222222222</span></label></div><div class="contact-wrapper"><input type="radio" id="contact-2" name="contact" value="Guillermo Perez" class="contact-item contact-2" style="display: none;"><label class="contact-label" for="contact-2">Guillermo Perez
    <span>Banco Scotiabank </span><span>#05555566666</span></label></div><div class="contact-wrapper"><input type="radio" id="contact-3" name="contact" value="Natalia Correa" class="contact-item contact-3" style="display: none;"><label class="contact-label" for="contact-3">Natalia Correa
    <span>Banco de Chile </span><span>#044444433333</span></label></div><div class="contact-wrapper"><input type="radio" id="contact-4" name="contact" value="Jorge Correa" class="contact-item contact-4" style="display: none;"><label class="contact-label" for="contact-4">Jorge Correa
    <span>Banco Scotiabank </span><span>#08888888999</span></label></div><div class="contact-wrapper"><input type="radio" id="contact-5" name="contact" value="Gonzalo Mardones" class="contact-item contact-5" style="display: none;"><label class="contact-label" for="contact-5">Gonzalo Mardones
    <span>Banco de Chile </span><span>#042222222223</span></label></div><div class="contact-wrapper"><input type="radio" id="contact-6" name="contact" value="María Benavides" class="contact-item contact-6" style="display: none;"><label class="contact-label" for="contact-6">María Benavides
    <span>Banco Santander </span><span>#0777777777</span></label></div><div class="contact-wrapper"><input type="radio" id="contact-7" name="contact" value="Luffy Monkey D" class="contact-item contact-7" style="display: none;"><label class="contact-label" for="contact-7">Luffy Monkey D
    <span>Banco del East Blue </span><span>#5656565656</span></label></div></form><div class="input-wrapper"><input class="input input-text" type="number" id="transfer-amount" autocomplete="new-password" placeholder=" " name="amount" required="" step="0.01" min="0.01" max="10000.01"><label class="label input-label" for="transfer-amount">Enter amount</label></div><button id="button-SendMoney">Send Money</button></section></main><header><div class="header-container"><h1 class="logo">Nova<span>Wallet</span></h1><nav class="site-nav"><button class="menu-toggle" aria-controls="primary-navigation" aria-expanded="false"><span class="visually-hidden"></span><div class="hamburger" aria-hidden="true"></div></button><ul class="primary-navigation" id="primary-navigation" data-state="closed"><li>Home</li><li>Deposit</li><li>Send Money</li><li>Transactions</li><li>Logout</li></ul></nav></div></header><dialog class="modal msg-modal"><div class="wrapper dialog-wrapper"><h3 class="title">Error</h3><p>Please select a contact</p><button class="button close-button">Ok</button></div></dialog><dialog class="modal msg-modal"><div class="wrapper dialog-wrapper"><h3 class="title">Error</h3><p>Please enter a transfer amount</p><button class="button close-button">Ok</button></div></dialog><dialog class="modal msg-modal"><div class="wrapper dialog-wrapper"><h3 class="title">Error</h3><p>Invalid transfer amount</p><button class="button close-button">Ok</button></div></dialog><footer><div class="footer-container"><p>Designed and developed by ©Sara Rioseco 2024</p></div></footer></div></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
  crossorigin="anonymous"></script> -->


</body></html>