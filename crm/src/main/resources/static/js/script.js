function toggleSidebar() {
    document.querySelector('.sidebar').classList.toggle('active');
}

document.addEventListener('click', function(event) {
    const sidebar = document.querySelector('.sidebar');
    const sidebarButton = document.querySelector('.botaoSidebar');
    if (window.innerWidth <= 992 && !sidebar.contains(event.target) && !sidebarButton.contains(event.target)) {
        sidebar.classList.remove('active');
    }
});

function toggleDropdownEmpresas() {
    var dropdownMenu = document.getElementById('dropdownEmpresas').querySelector('.dropdown-menu');
    if (dropdownMenu.style.display === 'none' || dropdownMenu.style.display === '') {
        dropdownMenu.style.display = 'block';
    } else {
        dropdownMenu.style.display = 'none';
    }
}
function toggleDropdownChecklists() {
    var dropdownMenu = document.getElementById('dropdownChecklists').querySelector('.dropdown-menu');
    if (dropdownMenu.style.display === 'none' || dropdownMenu.style.display === '') {
        dropdownMenu.style.display = 'block';
    } else {
        dropdownMenu.style.display = 'none';
    }
}

function toggleDropdownFormularios() {
    var dropdownMenu = document.getElementById('dropdownFormularios').querySelector('.dropdown-menu');
    if (dropdownMenu.style.display === 'none' || dropdownMenu.style.display === '') {
        dropdownMenu.style.display = 'block';
    } else {
        dropdownMenu.style.display = 'none';
    }
}

document.getElementById('dropdownEmpresas').addEventListener('click', toggleDropdownEmpresas);
document.getElementById('dropdownChecklists').addEventListener('click', toggleDropdownChecklists);
document.getElementById('dropdownFormularios').addEventListener('click', toggleDropdownFormularios);