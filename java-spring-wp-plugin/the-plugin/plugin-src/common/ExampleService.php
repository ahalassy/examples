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

    function get_key_config()
    {
        return get_option(WPEP_OPTION_KEY);
    }

    function get_events() {
        $key = get_option(WPEP_OPTION_KEY);
        $url = get_option(WPEP_OPTION_URL);

        $response = wp_remote_get( $url . '/api/event', array(
            'headers' => [
                'x-api-key' => $key
            ]
        ));
        $status_code = $response['response']['code'];
		$success = 200 <= $status_code && $status_code < 300;
        return $success
            ? json_decode($response['body'])
            : false;
    }
}
