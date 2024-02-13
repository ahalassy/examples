<?php
if (!defined('ABSPATH')) {
    exit; // Exit if accessed directly.
}

class ExampleSpringWidget extends WP_Widget
{

    // Main constructor
    public function __construct()
    {
        parent::__construct(
            'example_spring_widget',
            __('Example Spring Widget', 'text_domain'),
            array(
                'customize_selective_refresh' => true,
            )
        );
    }

    // The widget form (for the backend )
    public function form($instance)
    {
        /* ... */
    }

    // Update widget settings
    public function update($new_instance, $old_instance)
    {
        /* ... */
    }

    // Display the widget
    public function widget($args, $instance)
    {
        require_once(__PDIR__.'/block/render.php');
    }
}
