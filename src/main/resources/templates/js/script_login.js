function login() {
    let erro_messageLogin = document.getElementById('error-message-login')
    const emailLogin = document.getElementById('email-login').value;
    const senhaLogin = document.getElementById('senha-login').value;
    const dataLogin = {
        email: emailLogin,
        senha: senhaLogin
    };

    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams(dataLogin).toString()
    };

    const url = 'http://localhost:8080/users/login';

    fetch(url, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(dataLogin => {
            console.log('Resposta:', dataLogin);

            if (dataLogin && dataLogin.senha && dataLogin.email) {
        

                // Salva o email no armazenamento local
                localStorage.setItem('email', dataLogin.email);

                window.location.href = 'rotas/tela-posts.html'; 
            }
        })
        .catch(error => {
            erro_messageLogin.innerText = 'E-mail ou senha inválida.';
            console.error('Erro na solicitação:', error);
        });
}

document.getElementById('logar-js').addEventListener('click', function (event) {
    event.preventDefault();
    login();
});
