// ------ Hamburger menu feature -------
const hamButton = document.getElementById("menu-toggle");
const list = document.getElementById("primary-navigation")

hamButton.addEventListener('click', () => {
    const isOpened = hamButton.getAttribute('aria-expanded') === 'true';
    isOpened ? closeMenu() : openMenu();
});

function openMenu() {
    hamButton.setAttribute('aria-expanded', 'true');
    list.setAttribute('data-state', 'opened');
}

function closeMenu() {
    hamButton.setAttribute('aria-expanded', 'false');
    list.setAttribute('data-state', 'closing');

    list.addEventListener(
        'animationend',
        () => list.setAttribute('data-state', 'closed'),
        { once: true }
    );
}

// ------ Modals ------

const buttonUser = document.getElementById("button-user");
const userModal = document.getElementById("user-modal");
const buttonUserModalClose = document.getElementById("user-modal-close");
buttonUser.addEventListener("click", (e) => {
    e.preventDefault();
    userModal.showModal();
})
buttonUserModalClose.addEventListener("click", (e ) => {
    e.preventDefault();
    userModal.close();
})
