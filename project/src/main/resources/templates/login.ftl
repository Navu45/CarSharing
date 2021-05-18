<#import 'layout.ftl' as page>
<@page.layout false>
    <form method="POST" action="/login">
        <h2>Log in</h2>
        <div class="mb-3 mt-3">
            <#if message?? || error??>
            <span class="alert <#if error??>alert-danger</#if> <#if message??>alert-success</#if>" role="alert">${message!" " + error!" "}</span>
            </#if>
        </div>

        <div class="form-group">
            <label>
                <input class="form-control"  name="username" type="text" placeholder="Username"
                       autofocus="autofocus"/>
            </label>
        </div>

        <div class="form-group">
            <label>
                <input class="form-control"  name="password" type="password" placeholder="Password"/>
            </label>
        </div>
        <button class="btn btn-primary mb-3" type="submit">Log In</button>

        <h4><a href="/registration">Create an account</a></h4>
    </form>
</@page.layout>