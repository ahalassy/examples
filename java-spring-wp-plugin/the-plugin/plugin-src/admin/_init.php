<?php

/** 
 * Admin hooks
 */

if (!defined('ABSPATH')) {
	exit; // Exit if accessed directly.
}

add_action('admin_notices', 'wpep_admin_warning');
function wpep_admin_warning()
{
	$error = get_query_var('error');
	error_log($error);
	if ($error) {
		$notice = <<<EOT
				<div class="notice notice-error">
					<p>$error</p>
				</div>
				EOT;

		echo $notice;
	}
}

add_action('admin_menu', 'cp_example_admin_init');
function cp_example_admin_init()
{
	require_once(__DIR__ . '/AdminController.php');

	$controller = new AdminController();
	$controller->add_submenu();
}

add_action('admin_action_test_config', 'wpep_admin_action_test');
function wpep_admin_action_test()
{
	$controller = new AdminController();
	$controller->test_configuration();
	$url = $_SERVER['HTTP_REFERER'];
	wp_redirect($url);
	exit();
}

add_action('admin_action_save_config', 'wpep_admin_action_save');
function wpep_admin_action_save()
{
	$controller = new AdminController();
	$controller->save_configuration();
	$url = $_SERVER['HTTP_REFERER'];
	wp_redirect($url);
	exit();
}

add_action( 'admin_notices', 'wpep_show_admin_notices');
function wpep_show_admin_notices(){
  $notice = get_option( 'wpep_test_connection_success', false );
  if( $notice ){
    delete_option( 'wpep_connection_success' );
    echo <<<HTML
		<div class="notice notice-success is-dismissible">
			<p>$notice</p>
		</div>
	HTML;
  }

  $notice = get_option( 'wpep_test_connection_fail', false );
  if( $notice ){
    delete_option( 'wpep_connection_fail' );
    echo <<<HTML
		<div class="notice notice-error is-dismissible">
			<p>$notice</p>
		</div>
	HTML;
  }
}
