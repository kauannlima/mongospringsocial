const nomeUsuarioElement = document.getElementById('nomeUsuario');
const storedNome = localStorage.getItem('nome');
const storedId = localStorage.getItem('id');
const storedEmail = localStorage.getItem('email');
nomeUsuarioElement.innerHTML = storedNome;

function updateUser() {
    const erro_messageUpdate = document.getElementById('error-message-update');
    const newName = document.getElementById('nome-update').value;
    const novaSenha = document.getElementById('novaSenha-update').value;
    const RepitanovaSenha = document.getElementById('RepitaNovaSenha-update').value;

    if (novaSenha == RepitanovaSenha) {

        const dataUpdate = {
            nome: newName,
            email: storedEmail,
            senha: novaSenha,
        };

        const requestOptions = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dataUpdate),
        };

        const url = `http://localhost:8080/users/${storedId}`;

        fetch(url, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.text(); // Alterado para text() em vez de json()
            })
            .then(dataUpdate => {
                console.log('Resposta:', dataUpdate);

            })
            .catch(error => {
                console.error('Erro na solicitação:', error);
            });
 
        erro_messageUpdate.innerHTML = "<p class='sucesso'>Cadastro atualizado<p>"
    }
    else if (novaSenha != RepitanovaSenha) {
        erro_messageUpdate.innerHTML = "<p>Senha diferentes<p>"
    }

}

document.getElementById('update-js').addEventListener('click', function (event) {
    event.preventDefault();
    updateUser();
});
