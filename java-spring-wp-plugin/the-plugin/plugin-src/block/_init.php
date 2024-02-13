<?php
if (!defined('ABSPATH')) {
    exit; // Exit if accessed directly.
}

require_once(__PDIR__ . '/common/_const.php');
require_once(__PDIR__ . '/block/ExampleSpringWidget.php');

function my_register_spring_widget() {
	register_widget( 'ExampleSpringWidget' );
}
add_action( 'widgets_init', 'my_register_spring_widget' );
