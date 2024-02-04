document.getElementById('registar-js').addEventListener('click', function (event) {
    event.preventDefault();
    register();
});

function register() {
    let erro_messageRegister = document.getElementById('error-message-register');
    const nomeRegister = document.getElementById('nome-register').value;
    const emailRegister = document.getElementById('email-register').value;
    const senhaRegister = document.getElementById('senha-register').value;
    const dataRegister = {
        nome: nomeRegister,
        email: emailRegister,
        senha: senhaRegister
    };

    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dataRegister)
    };

    const url = 'http://localhost:8080/users';

    fetch(url, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            if (data.trim() !== '') {
                const jsonData = JSON.parse(data);
                if (jsonData && jsonData.id && jsonData.email) {
                    console.log('Registro bem-sucedido:', jsonData);
                }
            } else {
                erro_messageRegister.classList.add("sucesso"); 
                erro_messageRegister.innerText = 'Usuário cadastrado com sucesso';
            }
        })
        .catch(error => {
            erro_messageRegister.innerText = 'E-mail já cadastrado';
            console.error('Erro na solicitação:', error);
        });
}
