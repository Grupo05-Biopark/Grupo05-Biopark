
$(document).ready(function () {
    $("#loginForm").on("submit", function (event) {
      event.preventDefault();

      var email = $("#email").val();
      var senha = $("#senha").val();

      // Função de validação dos campos e-mail e senha
      function validarCampos(email, senha) {
        // Vai Verifica se o campo de e-mail está preenchido e é válido
        if (!email) {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Por favor, preencha o campo de e-mail.",
          });
          return false;
        }

        // Vai Verifica se o e-mail tem um formato válido
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Por favor, insira um e-mail válido.",
          });
          return false;
        }
        // Validar o e-mail com o  que esta cadastrado no banco de dados(Ainda não implementado)
        if (email.length < 5) {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "E-mail inválido. Por favor, insira um e-mail válido.",
          });
          return false;
        }
        // valida se a senha foi preenchida
        if (!senha) {
          Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Por favor, preencha o campo de senha.",
          });
          return false;
        }
        // Validar com o banco de dados a senha(Ainda não implementado)
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

      var camposValidos = validarCampos(email, senha);
      if (!camposValidos) {
        return;
      }

      $.ajax({
        url: "/login",
        method: "POST",
        data: {
          email: email,
          senha: senha,
        },
        success: function (response) {
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
          Swal.fire({
            icon: "error",
            title: "Erro",
            text:
              xhr.responseJSON.error ||
              "Falha ao realizar login. Verifique suas credenciais e tente novamente.",
          });
        },
      });
    });
  });