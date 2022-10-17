let btnlogout = document.querySelector('#btnlogout')
btnlogout.addEventListener('click', gologout)
function gologout() {
    if(confirm('로그아웃 하시겠습니까?'))
    { location.href = '/logout' };
}