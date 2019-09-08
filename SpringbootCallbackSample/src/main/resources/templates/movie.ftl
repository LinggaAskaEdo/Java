<!DOCTYPE html>
<html lang="">
<head>
    <title>MOVIE</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<p>
    Message: ${message}
    <br>
    Desc: ${desc}
</p>

<#if time??>
    <p>Date and time: ${time}</p>
</#if>

</body>
</html>