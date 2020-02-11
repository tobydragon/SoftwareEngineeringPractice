var sliderDial = document.getElementsByClassName('dial');
var initX = 0, initY = 0, curX = 0, curY = 0;
sliderDial[0].style.left = 35 + "px";
sliderDial[0].onmousedown = dragSliderDial;
sliderDial[0].onmouseover = playHoverSFX;
atBorder = true;
var locked = false;
var sfx_hover = new Audio("sfx/Hover.wav");
var sfx_press = new Audio("sfx/Press.wav");
var sfx_border = new Audio("sfx/Border.wav");
var sfx_correct = new Audio("sfx/ES_Correct.wav");
var sfx_unlock = new Audio("sfx/ES_Unlock.wav");
var sfx_enterNum = new Audio("sfx/ES_EnterNum.wav");
var sfx_incorrect = new Audio("sfx/ES_Incorrect.wav");
var sfx_submitHover  = new Audio("sfx/ES_ButtonHover.wav");
var sfx_submitPush  = new Audio("sfx/ES_ButtonPush.wav");
var element_ticVal = document.getElementsByClassName('ticket-val');
var lock_status = document.getElementsByClassName('lock-status-panel');
var minSlideVal = 35;
var maxSlideVal = 335;
var curLock = 0;
var correctPassword = [23, 65, 5, 89];
var curCombo = [false,false,false,false];
document.getElementsByClassName('submit-button')[0].onmouseover = submitHover;

function submitHover(){
    playProperly(sfx_submitHover);
}

function submitNum(){
    playProperly(sfx_submitPush);
    if(!locked){
        console.log(parseInt(element_ticVal[0].innerText));
        if(parseInt(element_ticVal[0].innerText) == correctPassword[curLock]){
            curCombo[curLock] = true;
        }else{
            curCombo[curLock] = false;
        }
        playProperly(sfx_enterNum);
        lock_status[0].childNodes[1 + (2*curLock)].style.backgroundColor= "yellow";
        if(curLock != 3){
            curLock += 1;
        }else{
            locked = true;
            if(!isCorrectCombo()){
                playProperly(sfx_incorrect);
                curLock = 0
                setTimeout(resetLock, 1000)
            }else{
                playProperly(sfx_unlock);
                playProperly(sfx_correct);
            }
        }
    }
}

function isCorrectCombo(){
    var isCorrect = true;
    for(var i = 0; i < 4; i++){
        if(curCombo[i] != true){
            lock_status[0].childNodes[1 + (2*i)].style.backgroundColor= "red";
            isCorrect = false;
        }else{
            lock_status[0].childNodes[1 + (2*i)].style.backgroundColor= "#97d8b2";
        }
    }
    return isCorrect;
}

function resetLock(){
    for(var i = 0; i < 4; i++){
        lock_status[0].childNodes[1 + (2*i)].style.backgroundColor= "gray";
    }
    locked = false;
}

function playHoverSFX(e){
    playProperly(sfx_hover);
    //lock_status[0].childNodes[1].style.backgroundColor= "#97d8b2";
    console.log("Hover");
}

function dragSliderDial(e){
    playProperly(sfx_press);
    initX = e.clientX;
    initY = e.clientY;
    console.log("Click");
    sliderDial[0].onmouseup = endDrag;
    sliderDial[0].onmouseout = endDrag;
    sliderDial[0].onmousemove = executeDrag;
}

function endDrag(e){
    sliderDial[0].onmouseup = null;
    sliderDial[0].onmouseout = null;
    sliderDial[0].onmousemove = null;
}

function executeDrag(e){
    curX = initX - e.clientX;
    initX = e.clientX;
    var currentXPos = sliderDial[0].offsetLeft - curX;
    if(parseInt(sliderDial[0].style.left.substring(0, sliderDial[0].style.left.length - 2)) >= minSlideVal && parseInt(sliderDial[0].style.left.substring(0, sliderDial[0].style.left.length - 2)) <= maxSlideVal
    && currentXPos >= minSlideVal && currentXPos <= maxSlideVal){
        sliderDial[0].style.left = currentXPos + "px";
        element_ticVal[0].innerText = parseInt((((currentXPos-minSlideVal)/(maxSlideVal-minSlideVal)))*100).toString();
        if(parseInt(sliderDial[0].style.left.substring(0, sliderDial[0].style.left.length - 2)) > minSlideVal && parseInt(sliderDial[0].style.left.substring(0, sliderDial[0].style.left.length - 2)) < maxSlideVal){
            atBorder = false;
        }else{
            if(!atBorder){
                console.log("Hit Border");
                playProperly(sfx_border);
                atBorder = true;
            }
        }
        console.log(parseInt(sliderDial[0].style.left.substring(0, sliderDial[0].style.left.length - 2)));
    }
}

function playProperly(sfx){
    if (sfx.paused) {
        sfx.play();
    }else{
        sfx.pause();
        sfx.currentTime = 0;
    }
}