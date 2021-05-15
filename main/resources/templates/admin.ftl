<#import "layout.ftl" as page>
<@page.layout>
    <div class="container mt-5">
        <h1>User List</h1>
        <table>
            <tr>
                <td>Username</td>
                <td>Roles</td>
            </tr>
            <#list users as user>
                <tr>
                    <td>${user.getUsername()}</td>
                    <td>${user.getAuthorities()}</td>
                    <td>
                        <button><a href="/user/edit/${user.getId()}">Edit</a></button>
                    </td>
                </tr>
            </#list>
        </table>
    </div>
</@page.layout>