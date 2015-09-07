/*
 * Copyright (C) 2010 Paul Watts (paulcwatts@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.io.request;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;

/**
 * Retrieve details about a specific trip.
 * {@link http://code.google.com/p/onebusaway/wiki/OneBusAwayRestApi_TripDetails}
 *
 * @author Paul Watts (paulcwatts@gmail.com)
 */
public final class ObaTripDetailsRequest extends RequestBase
        implements Callable<ObaTripDetailsResponse> {

    protected ObaTripDetailsRequest(URI uri) {
        super(uri);
    }

    public static class Builder extends RequestBase.BuilderBase {

        public Builder(String tripId) throws UnsupportedEncodingException {
            super(getPathWithId("/trip-details/", tripId));
        }

        /**
         * Determines whether the full trip element is included in the references.
         * Defaults to 'true'
         *
         * @return This object.
         */
        public Builder setIncludeTrip(boolean includeTrip) {
            mBuilder.queryParam("includeTrip", String.valueOf(includeTrip));
            return this;
        }

        /**
         * Determines whether the schedule element is included.
         * Defaults to 'true'
         *
         * @return This object.
         */
        public Builder setIncludeSchedule(boolean includeSchedule) {
            mBuilder.queryParam("includeSchedule", String.valueOf(includeSchedule));
            return this;
        }

        /**
         * Determines whether the status element is included.
         * Defaults to 'true'
         *
         * @return This object.
         */
        public Builder setIncludeStatus(boolean includeStatus) {
            mBuilder.queryParam("includeStatus", String.valueOf(includeStatus));
            return this;
        }

        public ObaTripDetailsRequest build() throws URISyntaxException {
            return new ObaTripDetailsRequest(buildUri());
        }
    }

    /**
     * Helper method for constructing new instances.
     *
     * @param context The package context.
     * @param tripId  The tripId to request.
     * @return The new request instance.
     * @throws UnsupportedEncodingException 
     * @throws URISyntaxException 
     */
    public static ObaTripDetailsRequest newRequest(String tripId) throws UnsupportedEncodingException, URISyntaxException {
        return new Builder(tripId).build();
    }

    @Override
    public ObaTripDetailsResponse call() {
        return call(ObaTripDetailsResponse.class);
    }

    @Override
    public String toString() {
        return "ObaTripDetailsRequest [mUri=" + mUri + "]";
    }
}
