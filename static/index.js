async function getAllArticles(){
    const res = await fetch('http://localhost:8080/?page=1&size=3');
    const articles = await res.json();
    articles.content.forEach(article => articleToHtml(article))
    // console.log(article)
}

window.addEventListener('DOMContentLoaded', getAllArticles)

function articleToHtml({title, content}){
    const articleList = document.getElementById("article")
    articleList.insertAdjacentHTML('beforeend',
        `<h3>${title}</h3>
            <div>${content}</div>`)
}