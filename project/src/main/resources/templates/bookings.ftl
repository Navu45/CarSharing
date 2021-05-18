<#import "layout.ftl" as page>
<@page.layout false "Your trips | CarSharingApp">
    <table class="table">
        <thead>
        <tr>
            <th>Started in</th>
            <th>Finished in</th>
            <th>Service cost</th>
            <th>Activity</th>
        </tr>
        </thead>
        <tbody>
        <#list bookings as booking>
            <tr>
                <td>${booking.getStartTime()}</td>
                <#if booking.getFinishTime()?has_content>
                    <td>${booking.getFinishTime()}</td>
                <#else>
                    <td>Not finished</td>
                </#if>
                <td>${booking.getServiceCost()}</td>
                <td>${booking.isFinished()?then('inactive', 'active')}</td>
                <td>
                    <form method="post" action="/booking/close_b/${booking.getId()}">
                        <button class="btn btn-primary mb-2" type="submit">Close</button>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@page.layout>