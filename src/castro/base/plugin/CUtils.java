/* cPlugin
 * Copyright (C) 2013 Norbert Kawinski (norbert.kawinski@gmail.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package castro.base.plugin;

import java.util.Calendar;


public class CUtils
{
	public static int getCurrentTimeInServerTicks()
	{
		// Some info:
		// 0:00AM = 18000
		// +24h = +24k ticks = 42000
		Calendar cal	= Calendar.getInstance();
		float hours		= cal.get(Calendar.HOUR_OF_DAY);
		float minutes	= cal.get(Calendar.MINUTE) + hours*60;
		float seconds	= cal.get(Calendar.SECOND) + minutes*60;
		
		float secondsInDay = 60*60*24;
		float timeOfDay = seconds / secondsInDay;
		
		// -6000=midnight; 6000=midday; 18000 = midnight
		// We have to forward time 6000
		float timeOffset = 18000;
		float currentTimeInTicks = (24000.f*timeOfDay) + timeOffset;
		return (int)currentTimeInTicks;
	}
}
