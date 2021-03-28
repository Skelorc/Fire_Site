/*Кнопка входа администратора.*/
function getDataFromInputs(e)
{
    e.preventDefault();
    let user = {
        login: document.querySelector(".input_login").value,
        password: document.querySelector(".input_password").value
    };

    sendData(user);
}

function sendData(text)
{
    /*fetch("http://192.168.0.13:8080/test?text=Hello World").then(result => console.log(result));*/
    console.log(text);
    fetch ("http://192.168.0.13:8080/reg", {method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(text)})
        .then(result => console.log(result))
}
let sendButton = document.querySelector(".form_send_btn");
sendButton.addEventListener("click", getDataFromInputs)




/*Кнопка добавления статей.*/

function getInfoAboutArticleFromForm(e)
{
    e.preventDefault();
    let article = {
        title: document.querySelector("#title").value,
        message: document.querySelector("#editor").value
    };
    sendArticle(article)
}
function sendArticle(article)
{
    console.log(article);

}
