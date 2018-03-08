<?php
$result = '[[CONFIG_NOT_FOUND/IS_ARRAY]]';
foreach (glob("www/altamides/app/web/configs/*.php") as $filename) {
	if(strpos($filename, 'config.blacklistdb.menu.php') !== false){
		continue;
	}
	require_once($filename) ;
}
foreach (glob("www/altamides/app/vdir/configs/*.php") as $filename) {
    require_once($filename) ;
}

$myVar = $_GET['config'];

$is_null_check;
$is_array_check;
$is_set_check = false;

if(substr($myVar, 0, 1) == '$'){
        $is_set_check = isset(${$myVar});
}

eval('$is_null_check = is_null('.$myVar.');');
//var_dump($is_null_check);
eval('$is_array_check = is_array('.$myVar.');');
//var_dump($is_array_check);
//var_dump($is_set_check);

if($is_set_check || defined($myVar) || (substr($myVar, 0, 1) == '$' && !$is_null_check && !$is_array_check)){
        eval('$result = '.$myVar.';');
}
echo (is_bool($result)?($result?'true':'false'):$result);
?>