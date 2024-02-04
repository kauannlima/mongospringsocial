document.addEventListener('DOMContentLoaded', function () {
    const postContainer = document.getElementById('postContainer');
    const postButton = document.getElementById('postButton');
    const pesquisaPostInput = document.getElementById('pesquisaPostInput');

    postButton.addEventListener("click", function () {
        console.log(pesquisaPostInput.value);
        getPosts();
    });

    postarJs.addEventListener("click", function () {
        postar();
        getPosts();
    });

    async function getPosts() {
        try {
            let posts;

            const title = pesquisaPostInput.value;

            let response = await fetch('http://localhost:8080/posts');
            posts = await response.json();

            if (title !== "") {
                response = await fetch(`http://localhost:8080/posts/titlesearch/${title}`);
                posts = await response.json();
            }

            postContainer.innerHTML = '';

            posts.forEach(post => {
                const postDiv = document.createElement('div');
                postDiv.classList.add('postDiv');

                // ADD AUTHOR
                const authorInfo = document.createElement('div');
                authorInfo.classList.add('author-info');
                authorInfo.innerHTML = `<p><img src="../src/img/user.png"> ${post.author.name}</p>`;
                postDiv.appendChild(authorInfo);

                // ADD TITLE E BODY DOS POSTS
                const postInfo = document.createElement('div');
                postInfo.classList.add('post-info');
                postInfo.innerHTML = `<p>${post.title}</p><p>${post.body}</p>`;
                postDiv.appendChild(postInfo);

                // IF QUE VERIFICA OS COMENTARIOS
                if (post.comments && post.comments.length > 0) {
                    const commentsInfo = document.createElement('div');
                    commentsInfo.classList.add('comments-info');

                    const linhaComentarios = document.createElement('div');
                    linhaComentarios.classList.add('linha-comentarios');
                    linhaComentarios.innerHTML = 'Todos os comentários:';
                    commentsInfo.appendChild(linhaComentarios);

                    // ADD OS COMENTARIOS E O AUTHOR
                    post.comments.forEach(comment => {
                        const commentDiv = document.createElement('div');
                        commentDiv.classList.add('comment');
                        commentDiv.innerHTML = `<p class="author"><img src="../src/img/user.png"> ${comment.authorDTO.name}</p><p>${comment.text}</p>`;
                        commentsInfo.appendChild(commentDiv);
                    });

                    postDiv.appendChild(commentsInfo);
                }

                postContainer.appendChild(postDiv);
            });

        } catch (error) {
            console.error('Erro ao obter posts:', error);
        }
    }

    getPosts();
    getUsuarios();
});

//CRIEI ESSE METODO APENAS PARA ELE FAZER UM GET DO USUARIO LOGADO E RETORNAR O NOME DO USUARIO
function getUsuarios() {
    const storedEmail = localStorage.getItem('email');
    fetch(`http://localhost:8080/users/email/${storedEmail}`, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "GET"
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro ao obter dados: ${response.statusText}`);
            }
            return response.json();
        })
        .then(usuario => {
            const nomeUsuarioElement = document.getElementById('nomeUsuario');
            localStorage.setItem('nome', usuario.nome);
            localStorage.setItem('id', usuario.id);
            nomeUsuarioElement.innerHTML = usuario.nome;

        })
        .catch(error => {
            console.error('Erro ao obter usuário:', error);
        });
}

//POST DOS POSTS :)
const erro_messageUpdate = document.getElementById('error-message-update');
const postarJs = document.getElementById('postar-js');

function postar() {
    const tituloInput = document.getElementById('tituloInput').value;
    const postagemInput = document.getElementById('postagemInput').value;
    const storedNome = localStorage.getItem('nome');
    const storedId = localStorage.getItem('id');

    if (tituloInput !== "" && postagemInput !== "") {

        const dataPost = {
            title: tituloInput,
            body: postagemInput,
            author: {
                id: storedId,
                name: storedNome
            }
        };

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dataPost)
        };

        const url = 'http://localhost:8080/posts';

        fetch(url, requestOptions)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
            })
            .then(dataPost => {
                if (dataPost && dataPost.title && dataPost.body) {
                    console.log("Não vazios");
                }
            })
            .catch(error => {
                console.error('Erro na solicitação:', error);
            });

        document.getElementById('tituloInput').value = '';
        document.getElementById('postagemInput').value = '';

        erro_messageUpdate.innerHTML = "<p class='sucesso'>Publicação postada!<p>";
    } else {
        erro_messageUpdate.innerHTML = "<p>Título do Post ou o corpo da postagem não podem estar vazios<p>";
    }
}
