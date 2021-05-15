<#import "layout.ftl" as page>
<@page.layout>
    <form action="@{/user/update}" method="post">
        <input type="text" name="username" th:value="${user.getUsername()}">
        <#list roles as item>
            <span>${role}"</span>
            <input type="checkbox" name="${role}" checked="${user.getAuthorities().contains(role)}">
            </span>
        </#list>
        <input type="hidden" name="user_id" value="${user.getId()}">
        <button type="submit">Save</button>
    </form>
</@page.layout>