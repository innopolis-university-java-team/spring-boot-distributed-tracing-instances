package org.spring.boot.distributed.tracing.instances.service.a.commons.filtering;

import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * The type Tracing filter.
 *
 * @author Alexander A. Kropotin
 * @project service -a
 * @created 2021 -08-08 20:03 <p>
 */
@Slf4j
@FieldDefaults(
        level = AccessLevel.PROTECTED,
        makeFinal = true
)
@Component("TracingFilter")
public abstract class TracingFilter extends OncePerRequestFilter {

    /**
     * The Trace id.
     */
    static String TRACE_ID = "traceId";

    /**
     * The Span id.
     */
    static String SPAN_ID = "spanId";

    /**
     * Gets span.
     *
     * @return the span
     */
    protected Span getSpan() {
        Span span = GlobalTracer.get().activeSpan();
        if (span == null) {
            span = GlobalTracer.get().buildSpan(this.getFilterName()).start();
        }

        return span;
    }
}