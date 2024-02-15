<?php
require_once(__PDIR__ . '/common/ExampleService.php');
$service = new ExampleService();
$events = $service->get_events();
?>
<div class="wp-spring">
    <h1>EVENTS</h1>
    <?php
    if ($events) {
        foreach ($events as $event) {
            $title = $event->title;
            $when = $event->when;
            $desc = $event->description;

            echo <<<HTML
                <h4><strong>$title</strong> - <small>$when</small></h3>
                <p>$desc</p>
            HTML;
        }
    } else {
        echo <<<HTML
            <p>Unfortunately the event list ist not available right now.</p>
        HTML;
    }
    ?>
</div>