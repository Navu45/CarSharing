<#import "layout.ftl" as page/>
<#import 'spring.ftl' as spring>
<@page.layout>
    <form method="post" object="${userForm}" action="/registration">
        <h2>Create your account</h2>

        <input type="text" placeholder="Username"
               autofocus="autofocus">
        <@spring.formInput "user.username"/>
        <@spring.showErrors '*', 'errors' />


        <input type="password" placeholder="Password">
        <@spring.formInput "user.password"/>
        <@spring.showErrors '*', 'errors' />


        <input type="password" placeholder="Confirm your password">
        <@spring.formInput "user.confirmPassword"/>
        <@spring.showErrors '*', 'errors' />

        <button type="submit">Submit</button>
    </form>
</@page.layout>