<!DOCTYPE html>
<html lang="">
    <head>
        <title>Message</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript">
            var DYN = (new Date%9e6).toString(0);

            window['COUNTER' + DYN] = 0;
            setTimeout(countDown,0);

            function countDown(){
                window['COUNTER' + DYN]--;
                if(window['COUNTER' + DYN] > 0){
                    setTimeout(countDown,0);
                }
                else
                {
                    $('#TEXT-CONTINUE').show();
                    $('#BTN-CONTINUE').show();
                }
            }

            $(document).ready(function(){
                $('#TEXT-CONTINUE').hide();
                $('#BTN-CONTINUE').hide();
            });
        </script>
    </head>
    <body class="tempdefault tempcolor tempone" onload="document.formRedirect.submit()">
        <section class="default-width">
            <form action="${url}" method="POST" id="formRedirect" name="formRedirect">
                <input type="submit" class="default-btn-page font-reg" id="BTN-CONTINUE" value="Continue">
            </form>
        </section>
    </body>
</html>