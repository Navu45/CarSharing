<#import "layout.ftl" as page>
<@page.layout false "Edit user (for admin) | CarSharingApp">
    <form action="/user/update" method="post">
        <div class="form-group">
            <label>
                <input class="form-control" type="text" readonly name="email" value="${user.getUsername()}">
            </label>
        </div>
        <#assign user_roles = user.getAuthorities()>
        <#list roles as role>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="${role}"
                        <#if user_roles?seq_contains(role)> checked="checked"</#if> id="${role}">
                <label class="form-check-label" for="${role}">
                    ${role}
                </label>
            </div>
        </#list>
        <div class="form-group">
            <label>
                <input class="form-control" type="hidden" name="user_id" value="${user.getId()}">
            </label>
        </div>
        <button class="btn btn-primary mb-2" type="submit">Save</button>
    </form>
</@page.layout>