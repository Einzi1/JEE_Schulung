<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>JAX-RS</title>
  </head>
  <body>
    <h1>Startseite von JAX-RS</h1>
    <ul>
        <li><a href="services/Basic/ping;info=test?s=kleiner Test">Ping-Test</a></li>
         
        <li><a href="services/Context/holeUriInfo?auswahl=eins&test=nein&auswahl=sieben">UrlInfo</a></li> 
        <li><a href="services/Context/holeHttpHeaders">HttpHeaders</a></li> 
        <li><a href="services/Context/holeRequest">Request</a></li> 
        <li><a href="services/Context/holeProviders">Providers</a></li> 

        <li><a href="services/json">Object per XML</a></li> 
        <li><a href="ajax.jsp">Ajax & REST</a></li> 
        
        
    </ul>
    <form action="services/Basic/formPing" method="POST">
    X: <input type="text" name="x" size="5"/> Y: <input type="text" name="y" size="5"/>
    <input type="submit" value=" = "/> 
    </form>
  </body>
</html> 
