<#import "layout.ftl" as page>
<@page.layout>
    <form th:method="post" th:action="@{booking/create}">
        <label>
            <input type="text" name="serviceCost" placeholder="Service cost">
        </label>
        <button type="submit">Book the car</button>
    </form>
    <table>
        <tr>
            <th>Started in</th>
            <th>Finished in</th>
            <th>Service cost</th>
            <th>Activity</th>
        </tr>
        <#list bookings as booking>
            <tr>
                <td>${booking.getStartTime()}</td>
                <td>${booking.getFinishTime() == null ? '---' : booking.getFinishTime()}"></td>
                <td>${booking.getServiceCost()}"></td>
                <td>${booking.isFinished() ? 'inactive' : 'active'}"></td>
                <td>
                    <form method="post" th:action="@{/booking/close/{id} (id = ${booking.getId()})}">
                        <button type="submit">Close</button>
                    </form>
                </td>
            </tr>
        </#list>
    </table>
</@page.layout>