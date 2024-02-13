<?php
if (!defined('ABSPATH')) {
    exit; // Exit if accessed directly.
}

require_once(__PDIR__ . '/common/_const.php');

class ExampleService
{

    function save_config($url, $mail, $key)
    {
        update_option(WPEP_OPTION_URL, $url);
        update_option(WPEP_OPTION_USER, $mail);
        update_option(WPEP_OPTION_KEY, $key);
    }

    function get_mail_config()
    {
        return get_option(WPEP_OPTION_USER);
    }

    function get_url_config()
    {
        return get_option(WPEP_OPTION_URL);
    }
}
