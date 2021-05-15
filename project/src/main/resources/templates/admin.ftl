<#import "layout.ftl" as page>
<@page.layout false>
    <div class="container mt-5">
        <h1>User List</h1>
        <table class="table">
            <thead>
            <tr>
                <td>Username</td>
                <td>Roles</td>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td>${user.getUsername()}</td>
                    <td>
                        <#list user.getAuthorities() as role>${role.toString()}<#sep >, </#list>
                    </td>
                    <td>
                        <a href="/user/edit/${user.getId()}">
                            <button class="btn btn-primary mb-2">Edit</button>
                        </a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@page.layout>