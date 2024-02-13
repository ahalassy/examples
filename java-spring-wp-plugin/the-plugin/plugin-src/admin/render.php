<?php
    require_once(__PDIR__.'/common/ExampleService.php');
    $service = new ExampleService();
    $url = $service->get_url_config();
    $mail = $service->get_mail_config();

?>
<form method="POST" action="<?=admin_url('admin.php'); ?>">
    <table class="form-table">
        <thead>
            <tr>
                <th>
                    <h1><?php _e('Setup Example Plugin') ?></h1>
                    <p><?php _e("Enter your credentials to establish connection between the example app and this WorkdPress plugin.") ?></p>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <label for="url"><?php _e("Server URL") ?></label><br />
                    <input type="text" name="url" value="<?=$url?>" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="email"><?php _e("E-mail address") ?></label><br />
                    <input type="text" name="email" value="<?=$mail?>" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password"><?php _e("Password") ?></label><br />
                    <input type="password" name="password" />
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit" class="button" name="action" value="test_config"><?php _e("Test Connection") ?></button>
                    <button type="submit" class="button button-primary" name="action" value="save_config"><?php _e("Save Connection") ?></button>
                </td>
            </tr>
        </tbody>
    </table>
</form>