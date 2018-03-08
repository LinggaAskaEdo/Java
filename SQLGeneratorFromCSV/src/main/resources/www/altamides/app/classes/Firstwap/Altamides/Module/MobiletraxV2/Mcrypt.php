<?php
namespace Firstwap\Altamides\Module\MobiletraxV2;

class mcrypt {
    private $mcryptKey;
    private $td;
    private $iv;
    private $blocksize;

    public function __construct($key = "", $iv="") {
        //$this->mcryptKey = $key;zauphaiDiemee2yoo1fochacie1axeibofoowai8oolaih1ooheesouNguxahmi9Buziewoh0vungal1PhooK9ThahDuy 
        $this->mcryptKey = \MOBILETRAX_ENCRYPTION_KEY;

        $this->td = mcrypt_module_open(MCRYPT_TripleDES, '', MCRYPT_MODE_CBC, '');
        $this->blocksize = mcrypt_get_block_size(MCRYPT_TripleDES, MCRYPT_MODE_CBC);
        $this->mcryptKey = substr($this->mcryptKey, 0, mcrypt_enc_get_key_size($this->td));
        //$this->iv = mcrypt_create_iv (mcrypt_get_iv_size(MCRYPT_TripleDES, MCRYPT_MODE_CBC), MCRYPT_DEV_URANDOM);
        //$this->iv = mcrypt_create_iv (mcrypt_get_iv_size(MCRYPT_TripleDES, MCRYPT_MODE_CBC), MCRYPT_DEV_RANDOM);
        $this->iv = $iv;//z\x90\xb9Q\xa7r\x87N";
    }

    public function getIv() {
        return $this->iv;
    }

    public function encrypt($text) {
        $enc = "";

        mcrypt_generic_init($this->td, $this->mcryptKey , $this->iv);
        $text = $this->pkcs5_pad($text);
        $enc = mcrypt_generic($this->td, $text);
        mcrypt_generic_deinit($this->td);

        return base64_encode($enc);
    }

    public function decrypt($text) {
        error_log('ccc data '.$text.', iv = '.$this->iv);
        $dec = "";
        //error_log("Pre 64dec : ".$text);
        //$text = trim(base64_decode($text));
        $text = base64_decode($text);
        //error_log("64dec : ".$text);
        
        mcrypt_generic_init($this->td, $this->mcryptKey , $this->iv);        
        
        $dec = mdecrypt_generic($this->td, $text);
        error_log("dec : ".$dec." == ".$this->pkcs5_unpad($dec));

        return $this->pkcs5_unpad($dec);
    }

    private function pkcs5_pad ($text) {
        $pad = $this->blocksize - (strlen($text) % $this->blocksize);

        return $text . str_repeat(chr($pad), $pad);
    }

    private function pkcs5_unpad($text) {
        $pad = ord($text{strlen($text)-1});
        if ($pad > strlen($text)) {
            return false;
        }
        if (strspn($text, chr($pad), strlen($text) - $pad) != $pad) {
            return false;
        }
        //return substr($text, 0, -1 * $pad); 
        return substr(rtrim($text, "\0"), 0, -1 * $pad);
    }
}

?>
