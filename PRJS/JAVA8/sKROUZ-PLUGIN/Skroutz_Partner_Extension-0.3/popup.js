

window.onload = onWindowLoad;

function onWindowLoad() {

  chrome.storage.sync.get(['uuid'], function(result) {
    console.log('Value currently is ' + result.uuid);
    document.getElementById('tags').value =  result.uuid ;
  });

  var message = document.querySelector('#message');

  chrome.tabs.executeScript(null, {
    file: "getPagesSource.js"
  }, function() {
    if (chrome.runtime.lastError) {
      message.innerText = 'There was an error injecting script : \n' + chrome.runtime.lastError.message;
    }
  });

}


var postUrl = 'http://localhost:4567/save';
var statusDisplay = null;
var itemsDisplay = null;


function sendItemToServer() {

//   scrollToBottom ();
//   scrollToTop ();


    event.preventDefault();

    var title = document.getElementById('title').value.trim();
    var url = document.getElementById('url').value.trim();
    var tags = document.getElementById('tags').value.trim();
    //var summary = document.getElementById('summary').value;


    var xhr = new XMLHttpRequest();
    xhr.open('POST', postUrl, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");





    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4) {
            statusDisplay.innerHTML = '';
            if (xhr.status == 200) {

                var  resultsUrl = '<br/><br/> <a   target="_blank" href="http://localhost:4567/cheapest?searchId='+  xhr.responseText  +'"> click here to check your results </a>'  ;
                statusDisplay.innerHTML = resultsUrl ;

                document.getElementById('tags').value = xhr.responseText;

              //  itemsDisplay.innerHTML = xhr.responseText ;

                chrome.storage.sync.set({uuid:  xhr.responseText}, function() {
                   console.log('Value is set to ' +  xhr.responseText );
                });

                //window.setTimeout(window.close, 1000);

            } else {
                statusDisplay.innerHTML = 'Error saving: ' + xhr.statusText;
            }
        }
    };


     var delimiter = "<span id='delimiter' class='Delimiter'></span>";

     var payload = "";
     payload = payload  + tags  + delimiter ;
     payload = payload  + title + delimiter ;
     payload = payload  + url   + delimiter;
     payload = payload  + document.querySelector('#message').innerHTML;
     xhr.send(  payload );
     statusDisplay.innerHTML = 'Saving...';


}



window.addEventListener('load', function(evt) {

    statusDisplay = document.getElementById('status-display');
    itemsDisplay = document.getElementById('items-display');

    document.getElementById('sendItemToServer').addEventListener('submit', sendItemToServer );

    document.getElementById('clearStorage').addEventListener('submit', clearStorage );

    chrome.runtime.getBackgroundPage(function(eventPage) {
        eventPage.getPageDetails(onPageDetailsReceived);
    });
});

function clearStorage() {
    chrome.storage.sync.set({uuid:  'undefined'}  );
    document.getElementById('tags').value = 'undefined';
}

chrome.runtime.onMessage.addListener(function(request, sender) {
  if (request.action == "getSource") {
    message.innerText = request.source;
  }
});


function onPageDetailsReceived(pageDetails)  {
    document.getElementById('title').value = pageDetails.title;
    document.getElementById('url').value = pageDetails.url;
   //document.getElementById('summary').innerText = pageDetails.summary;
}

function b64EncodeUnicode(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g,
        function toSolidBytes(match, p1) {
            return String.fromCharCode('0x' + p1);
    }));
}

function b64DecodeUnicode(str) {
    return decodeURIComponent(atob(str).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
}



//
//scrollingElement = (document.scrollingElement || document.body)
//function scrollToBottom () {
//   scrollingElement.scrollTop = scrollingElement.scrollHeight;
//}
//
//function scrollToTop (id) {
//   scrollingElement.scrollTop = 0;
//}
//
////Require jQuery
//function scrollSmoothToBottom (id) {
//   $(scrollingElement).animate({
//      scrollTop: document.body.scrollHeight
//   }, 500);
//}
//
////Require jQuery
//function scrollSmoothToTop (id) {
//   $(scrollingElement).animate({
//      scrollTop: 0
//   }, 500);
//}

