function getXMLHttpRequest() {
  if (window.ActiveXObject) {
    try {
      return new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e1) {
      try {
        return new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
        return null;
      }
    }
  } else if (window.XMLHttpRequest) {
    return new XMLHttpRequest();
  } else {
    return null;
  }
}

var httpRequest = null;

function sendRequest(url, params, callback, method) {
  httpRequest = getXMLHttpRequest();

  var httpMethod = method ? method : "GET";
  if (httpMethod != "GET" && httpMethod != "POST") {
    httpMethod = "GET";
  }
  var httpParams = params == null || params == "" ? null : params;
  var httpUrl = url;
  if (httpMethod == "GET" && httpParams != null) {
    httpUrl = httpUrl + "?" + httpParams;
  }

  //alert("method == " + httpMethod + "\turl == " + httpUrl + "\tparam == " + httpParams);
  httpRequest.open(httpMethod, httpUrl, true);
  httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  httpRequest.onreadystatechange = callback;
  //alert(httpMethod == 'POST' ? httpParams : null);
  httpRequest.send(httpMethod == "POST" ? httpParams : null);
}
