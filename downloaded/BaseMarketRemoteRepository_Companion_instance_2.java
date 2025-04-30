package p035ru.unicorn.ujin.data.repository;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import p035ru.unicorn.ujin.data.repository.BaseMarketRemoteRepository;

@Metadata(mo51341bv = {1, 0, 3}, mo51342d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo51343d2 = {"<anonymous>", "Lru/unicorn/ujin/data/repository/BaseRemoteRepository;", "invoke"}, mo51344k = 3, mo51345mv = {1, 4, 1})
/* renamed from: ru.unicorn.ujin.data.repository.BaseMarketRemoteRepository$Companion$instance$2 */
/* compiled from: BaseMarketRemoteRepository.kt */
final class BaseMarketRemoteRepository$Companion$instance$2 extends Lambda implements Function0<BaseRemoteRepository> {
    public static final BaseMarketRemoteRepository$Companion$instance$2 INSTANCE = new BaseMarketRemoteRepository$Companion$instance$2();

    BaseMarketRemoteRepository$Companion$instance$2() {
        super(0);
    }

    public final BaseRemoteRepository invoke() {
        return BaseMarketRemoteRepository.Holder.INSTANCE.getINSTANCE();
    }
}
