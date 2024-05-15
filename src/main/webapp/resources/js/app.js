// ------ Hamburger menu feature -------
const bttn = document.getElementById("menu-toggle");
const list = document.getElementById("primary-navigation")

bttn.addEventListener('click', () => {
    const isOpened = bttn.getAttribute('aria-expanded') === 'true';
    isOpened ? closeMenu() : openMenu();
});

function openMenu() {
    bttn.setAttribute('aria-expanded', 'true');
    list.setAttribute('data-state', 'opened');
}

function closeMenu() {
    bttn.setAttribute('aria-expanded', 'false');
    list.setAttribute('data-state', 'closing');

    list.addEventListener(
        'animationend',
        () => list.setAttribute('data-state', 'closed'),
        { once: true }
    );
}

// ------ Modals ------

const bttnUser = document.getElementById("button-user");
const userModal = document.getElementById("user-modal");
const bttnUserModalClose = document.getElementById("user-modal-close");
bttnUser.addEventListener("click", (e) => {
    e.preventDefault();
    userModal.showModal();
})
bttnUserModalClose.addEventListener("click", (e ) => {
    e.preventDefault();
    userModal.close();
})

console.log("Hola, estoy corriendo JS")