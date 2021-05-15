<#import 'layout.ftl' as page>
<@page.layout>
    <form method="POST" action="/login">
        <h2>Log in</h2>

        <span>${message}</span>
        <input name="username" type="text" placeholder="Username"
               autofocus="autofocus"/>

        <input name="password" type="password" placeholder="Password"/>
        <span>${error}</span>

        <button type="submit">Log In</button>

        <h4><a href="/registration">Create an account</a></h4>
    </form>
</@page.layout>