<#import "layout.ftl" as page>
<@page.layout>
    <h1>Welcome!</h1>
    <form action="/logout" method="post">
        <input type="submit" value="Sign Out"/>
    </form>
</@page.layout>