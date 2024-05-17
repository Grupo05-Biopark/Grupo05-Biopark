    $(document).ready(function () {
        $("#loginForm").on("submit", function (event) {
            event.preventDefault();

            var email = $("#email").val();
            var senha = $("#senha").val();

            // Verificação dos campos
            var camposValidos = email.length > 0 && senha.length > 0;
            if (!camposValidos) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Por favor, preencha todos os campos obrigatórios.'
                });
                return;
            }

            // Envio do formulário via AJAX
            $.ajax({
                url: "/login",
                method: "POST",
                data: {
                    email: email,
                    senha: senha
                },
                success: function (response) {
                    Swal.fire({
                        icon: "success",
                        title: "Sucesso",
                        text: "Login realizado com sucesso!",
                        showConfirmButton: false,
                        timer: 1500
                    }).then(function () {
                        window.location.href = "/empresas/listar";
                    });
                },
                error: function (xhr, status, error) {
                    Swal.fire({
                        icon: "error",
                        title: "Erro",
                        text: "Falha ao realizar login. Verifique suas credenciais e tente novamente."
                    });
                }
            });
        });
    });
