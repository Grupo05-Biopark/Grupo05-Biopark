// Context: JavaScript file for 'login.html'
    $(document).ready(function () {
        $("#loginForm").on("submit", function (event) {
            event.preventDefault();

            var email = $("#email").val();
            var senha = $("#senha").val();

            function mostrarIndicadorDeCarregamento() {
                $("#loadingIndicator").show();
            }

            function esconderIndicadorDeCarregamento() {
                $("#loadingIndicator").hide();
            }

            function validarCampos(email, senha) {
                if (!email) {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Por favor, preencha o campo de e-mail.",
                    });
                    return false;
                }

                var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailRegex.test(email)) {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Por favor, insira um e-mail válido.",
                    });
                    return false;
                }

                if (!senha) {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Por favor, preencha o campo de senha.",
                    });
                    return false;
                }

                if (senha.length < 8) {
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "A senha deve ter pelo menos 8 caracteres.",
                    });
                    return false;
                }

                return true;
            }

            if (validarCampos(email, senha)) {
                mostrarIndicadorDeCarregamento();

                $.ajax({
                    url: "/login",
                    method: "POST",
                    data: {
                        email: email,
                        senha: senha
                    },
                    success: function (response) {
                        esconderIndicadorDeCarregamento();
                        Swal.fire({
                            icon: "success",
                            title: "Sucesso",
                            text: "Login realizado com sucesso!",
                            showConfirmButton: false,
                            timer: 2000,
                        }).then(function () {
                            window.location.href = "/dashboard";
                        });
                    },
                    error: function (xhr, status, error) {
                        esconderIndicadorDeCarregamento();
                        Swal.fire({
                            icon: "error",
                            title: "Erro",
                            text: xhr.responseJSON.error || "Falha ao realizar login. Verifique suas credenciais e tente novamente.",
                        });
                    }
                });
            }
        });
    });
