function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("openNav").style.color = "white"; // Restaurar a cor original do botão ao fechar
}

// Função para fechar a navbar
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("openNav").style.color = "black"; // Mudar a cor do botão ao abrir
}

// Evento de clique para abrir a navbar ao clicar no botão
document.getElementById("openNav").addEventListener("click", function() {
    if (document.getElementById("mySidenav").style.width === "0px" || document.getElementById("mySidenav").style.width === "") {
        openNav();
    } else {
        closeNav();
    }
});