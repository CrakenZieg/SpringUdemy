<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>
    </head>
    <body>
        <table border="1">
            <caption>Listado</caption>
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Email</th>
                <th>Teléfono</th>
            </tr>
            <c:forEach var="persona" items="${personas}">
                <tr>
                    <th><a href="/PersonasWebHibernateJPA/ServletFormulario?id=${persona.id}">${persona.id}</a></th>
                    <th>${persona.nombre}</th>
                    <th>${persona.apellido}</th>
                    <th>${persona.email}</th>
                    <th>${persona.telefono}</th>                    
                </tr>
            </c:forEach>
        </table>
        <button><a href="/PersonasWebHibernateJPA/ServletNuevaPersona">Agregar</a></button>
    </body>
</html>
