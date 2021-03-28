function getDate(element)
{
    let date = new Date();
    let fullDate =date.getHours() +":" + date.getMinutes() + ".\n"+ date.getDate()+ "-" + date.getMonth()+1+ "-" + date.getFullYear();
    element.innerHTML= fullDate;
}
let dateSpan = document.querySelector(".date_now");
dateSpan.addEventListener("onload", getDate(dateSpan));
