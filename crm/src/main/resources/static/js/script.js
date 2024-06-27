const sidebarToggle = document.querySelector("[data-mdb-collapse-init]");
const sidebar = document.getElementById("sidebarMenu");
const body = document.body;

// Event listener para abrir/fechar a barra lateral
sidebarToggle.addEventListener("click", function() {
    sidebar.classList.toggle("show");
    body.classList.toggle("sidebar-open");
});

// Event listener para fechar a barra lateral ao clicar fora dela
document.addEventListener("click", function(event) {
    const isClickInsideSidebar = sidebar.contains(event.target);
    const isClickInsideSidebarToggle = sidebarToggle.contains(event.target);

    if (!isClickInsideSidebar && !isClickInsideSidebarToggle) {
        sidebar.classList.remove("show");
        body.classList.remove("sidebar-open");
    }
});


// Função para fechar todos os menus suspensos
function closeAllDropdowns() {
    var dropdowns = document.querySelectorAll('.dropdown-menu');
    dropdowns.forEach(function(dropdown) {
        dropdown.style.display = 'none';
    });
}

// Event listener para fechar os menus suspensos ao clicar fora deles
document.addEventListener("click", function(event) {
    var isClickInsideDropdown = false;
    var dropdowns = document.querySelectorAll('.dropdown-toggle');
    dropdowns.forEach(function(dropdown) {
        if (dropdown.contains(event.target)) {
            isClickInsideDropdown = true;
        }
    });

    if (!isClickInsideDropdown) {
        closeAllDropdowns();
    }
});

// Funções para alternar cada menu suspenso
function toggleDropdownEmpresas() {
    closeAllDropdowns();
    var dropdownMenu = document.getElementById('dropdownEmpresas').querySelector('.dropdown-menu');
    if (dropdownMenu.style.display === 'none' || dropdownMenu.style.display === '') {
        dropdownMenu.style.display = 'block';
    }
}

function toggleDropdownChecklists() {
    closeAllDropdowns();
    var dropdownMenu = document.getElementById('dropdownChecklists').querySelector('.dropdown-menu');
    if (dropdownMenu.style.display === 'none' || dropdownMenu.style.display === '') {
        dropdownMenu.style.display = 'block';
    }
}

function toggleDropdownFormularios() {
    closeAllDropdowns();
    var dropdownMenu = document.getElementById('dropdownFormularios').querySelector('.dropdown-menu');
    if (dropdownMenu.style.display === 'none' || dropdownMenu.style.display === '') {
        dropdownMenu.style.display = 'block';
    }
}

function toggleDropdownUser() {
    closeAllDropdowns();
    var dropdownMenu = document.getElementById('dropdownUser').querySelector('.dropdown-menu');
    if (dropdownMenu.style.display === 'none' || dropdownMenu.style.display === '') {
        dropdownMenu.style.display = 'block';
    }
}

// Event listeners para cada menu suspenso
document.getElementById('dropdownEmpresas').addEventListener('click', toggleDropdownEmpresas);
document.getElementById('dropdownChecklists').addEventListener('click', toggleDropdownChecklists);
document.getElementById('dropdownFormularios').addEventListener('click', toggleDropdownFormularios);
document.getElementById('dropdownUser').addEventListener('click', toggleDropdownUser);